package com.prince.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;


@Entity(name="meta_location")
@Indexed
@AnalyzerDef(name = "customanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
    @Parameter(name = "language", value = "English")
  })
})
public class WorldCity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Field
	private String iso;
	
	@Field
	@Analyzer(definition = "customanalyzer")
	private String local_name;
	
	private String type;
	private Integer in_location;
	private Double geo_lat;
	private Double geo_lng;
	private String db_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIso() {
		return iso;
	}
	public void setIso(String iso) {
		this.iso = iso;
	}
	public String getLocal_name() {
		return local_name;
	}
	public void setLocal_name(String local_name) {
		this.local_name = local_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getIn_location() {
		return in_location;
	}
	public void setIn_location(Integer in_location) {
		this.in_location = in_location;
	}
	public Double getGeo_lat() {
		return geo_lat;
	}
	public void setGeo_lat(Double geo_lat) {
		this.geo_lat = geo_lat;
	}
	public Double getGeo_lng() {
		return geo_lng;
	}
	public void setGeo_lng(Double geo_lng) {
		this.geo_lng = geo_lng;
	}
	public String getDb_id() {
		return db_id;
	}
	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}
	@Override
	public String toString() {
		return "WorldCity [id=" + id + ", iso=" + iso + ", local_name="
				+ local_name + ", type=" + type + ", in_location="
				+ in_location + ", geo_lat=" + geo_lat + ", geo_lng=" + geo_lng
				+ ", db_id=" + db_id + "]";
	}
}
