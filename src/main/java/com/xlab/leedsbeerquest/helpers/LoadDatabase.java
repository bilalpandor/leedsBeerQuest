package com.xlab.leedsbeerquest.helpers;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.xlab.leedsbeerquest.entities.Address;
import com.xlab.leedsbeerquest.entities.Review;
import com.xlab.leedsbeerquest.entities.Venue;
import com.xlab.leedsbeerquest.repository.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(VenueRepository repository) throws IOException {
        //load CSV file
        List<CsvFileEntry> entries = readFromCsvFile("C:\\Users\\bilal\\Documents\\Projects\\leedsbeerquest\\src\\main\\resources\\leedsbeerquest.csv");

        return args -> {
            entries.forEach(entry -> {
                Address thisAddress = new Address(
                        entry.getAddress(),
                        Double.parseDouble(entry.getLat()),
                        Double.parseDouble(entry.getLng())
                );

                TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(entry.getDate());
                Instant i = Instant.from(ta);

                Review thisReview = new Review(
                            entry.getCategory(),
                            entry.getUrl(),
                            Date.from(i),
                            entry.getExcerpt(),
                            Double.parseDouble(entry.getStars_beer()),
                            Double.parseDouble(entry.getStars_atmosphere()),
                            Double.parseDouble(entry.getStars_amenities()),
                            Double.parseDouble(entry.getStars_value())
                    );


                Venue thisVenue = new Venue(
                        entry.getName(),
                        thisAddress,
                        entry.getPhone(),
                        entry.getTwitter(),
                        thisReview,
                        new ArrayList<String>(Arrays.asList(entry.getTags().split(","))),
                        entry.getThumbnail()
                );

                log.info("Preloading " + repository.save(thisVenue));
            });
        };
    }

    private List<CsvFileEntry> readFromCsvFile(String path) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(path), ',');
        HeaderColumnNameMappingStrategy<CsvFileEntry> beanStrategy = new HeaderColumnNameMappingStrategy<CsvFileEntry>();
        beanStrategy.setType(CsvFileEntry.class);

        CsvToBean<CsvFileEntry> csvToBean = new CsvToBean<CsvFileEntry>();
        List<CsvFileEntry> csvEntries = csvToBean.parse(beanStrategy, reader);

        System.out.println(csvEntries);
        reader.close();

        return csvEntries;
    }


}
