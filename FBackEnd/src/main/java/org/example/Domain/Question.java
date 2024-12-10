package org.example.Domain;

import jakarta.persistence.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

@jakarta.persistence.Entity
@Table(name = "Questions")
public class Question implements Entity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "type")
    private String type; // Ex: single-choice, multiple-choice, open-ended

    @Column(name = "options")
    private String optionsJson;

    @Column(name = "co2_values")
    private String co2ValuesJson;

    @Transient
    private List<String> options;

    @Transient
    private List<Double> co2Values;

    private static final Gson gson = new Gson();

    public Question() {}

    public Question(Integer id, String text, String type, List<String> options, List<Double> co2Values) {
        this.id = id;
        this.text = text;
        this.type = type;
        setOptions(options);
        setCo2Values(co2Values);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getOptions() {
        if (options == null && optionsJson != null) {
            options = gson.fromJson(optionsJson, new TypeToken<List<String>>(){}.getType());
        }
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
        this.optionsJson = gson.toJson(options);
    }

    public List<Double> getCo2Values() {
        if (co2Values == null && co2ValuesJson != null) {
            co2Values = gson.fromJson(co2ValuesJson, new TypeToken<List<Double>>(){}.getType());
        }
        return co2Values;
    }

    public void setCo2Values(List<Double> co2Values) {
        this.co2Values = co2Values;
        this.co2ValuesJson = gson.toJson(co2Values);
    }
}
