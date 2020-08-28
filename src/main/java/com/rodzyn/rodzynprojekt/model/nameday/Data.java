
package com.rodzyn.rodzynprojekt.model.nameday;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dates",
    "namedays"
})
public class Data {

    @JsonProperty("dates")
    private Dates dates;
    @JsonProperty("namedays")
    private Namedays namedays;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dates")
    public Dates getDates() {
        return dates;
    }

    @JsonProperty("dates")
    public void setDates(Dates dates) {
        this.dates = dates;
    }

    @JsonProperty("namedays")
    public Namedays getNamedays() {
        return namedays;
    }

    @JsonProperty("namedays")
    public void setNamedays(Namedays namedays) {
        this.namedays = namedays;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Data{" +
                "dates=" + dates +
                ", namedays=" + namedays +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
