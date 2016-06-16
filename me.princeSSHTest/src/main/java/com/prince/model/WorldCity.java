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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((db_id == null) ? 0 : db_id.hashCode());
		result = prime * result + ((geo_lat == null) ? 0 : geo_lat.hashCode());
		result = prime * result + ((geo_lng == null) ? 0 : geo_lng.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((in_location == null) ? 0 : in_location.hashCode());
		result = prime * result + ((iso == null) ? 0 : iso.hashCode());
		result = prime * result + ((local_name == null) ? 0 : local_name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorldCity other = (WorldCity) obj;
		if (db_id == null) {
			if (other.db_id != null)
				return false;
		} else if (!db_id.equals(other.db_id))
			return false;
		if (geo_lat == null) {
			if (other.geo_lat != null)
				return false;
		} else if (!geo_lat.equals(other.geo_lat))
			return false;
		if (geo_lng == null) {
			if (other.geo_lng != null)
				return false;
		} else if (!geo_lng.equals(other.geo_lng))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (in_location == null) {
			if (other.in_location != null)
				return false;
		} else if (!in_location.equals(other.in_location))
			return false;
		if (iso == null) {
			if (other.iso != null)
				return false;
		} else if (!iso.equals(other.iso))
			return false;
		if (local_name == null) {
			if (other.local_name != null)
				return false;
		} else if (!local_name.equals(other.local_name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
