package com.github.nurkholik.ddi.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.apachecommons.CommonsLog;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@CommonsLog
public class Utils {

	private static final String datePattern = "yyyy-MM-dd HH:mm:ss";
	private static ObjectMapper mapper = null;

	public static ObjectMapper objectMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			mapper.setDateFormat(new SimpleDateFormat(datePattern));
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.disable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

			SimpleModule module = new SimpleModule();
			module.addSerializer(Double.class, new DoubleSerializer());
			module.addSerializer(Long.class, new LongSerializer());
			module.addSerializer(Float.class, new FloatSerializer());
			mapper.registerModule(module);
		}
		return mapper;
	}

	public static String toJson(Object object) {
		try {
			return objectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("Parsing JSON Failed : " + e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static java.util.Map<String, Object> toMap(Object object) {
		return objectMapper().convertValue(object, java.util.Map.class);
	}

	public static Map<String, String> stringJsonToMap(String json) throws JsonProcessingException {
		Map<String, String> ret = new HashMap<>();
		if (json != null) {
			ret = objectMapper().convertValue(objectMapper().readTree(json), Map.class);
		}
		return ret;
	}

	public static <T> T fromJson(String json, Class<T> toValueType) {
		return objectMapper().convertValue(json, toValueType);
	}

	public static class DoubleSerializer extends com.fasterxml.jackson.databind.JsonSerializer<Double> {
		@Override
		public void serialize(Double t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeNumber(new BigDecimal(t));
		}
	}

	public static class FloatSerializer extends com.fasterxml.jackson.databind.JsonSerializer<Float> {
		@Override
		public void serialize(Float t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeNumber(new BigDecimal(t));
		}
	}

	public static class LongSerializer extends com.fasterxml.jackson.databind.JsonSerializer<Long> {
		@Override
		public void serialize(Long t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeNumber(new BigDecimal(t));
		}
	}

	public static class StringSerializer extends com.fasterxml.jackson.databind.JsonSerializer<String> {
		@Override
		public void serialize(String t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeNumber("\"" + t + "\"");
		}
	}

	public static void main(String[] args) {
		Map<String, Object> ret = new HashMap<>();
		Long va = 1000000000000000000L;
		ret.put("va", va);

		System.out.println(toJson(ret));
	}


}
