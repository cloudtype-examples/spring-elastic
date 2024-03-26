package io.cloudtype.springelastic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "movies")
public class Movies {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "english_title")
    private String englishTitle;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field(type = FieldType.Date, name = "released_on")
    private LocalDate releasedOn;

    @Field(type = FieldType.Integer, name = "running_time")
    private Integer runningTime;

    @Field(type = FieldType.Text, name = "director")
    private String director;

    @Field(type = FieldType.Text, name = "distributor")
    private String distributor;

    @Field(type = FieldType.Text, name = "genre")
    private String genre;

    @Field(type = FieldType.Text, name = "language")
    private String language;




}
