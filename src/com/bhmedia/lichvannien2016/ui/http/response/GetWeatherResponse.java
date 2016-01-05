package com.bhmedia.lichvannien2016.ui.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Van on 1/24/2015.
 */
public class GetWeatherResponse extends BaseResponse {
    @SerializedName("data")
    public Data data;

    public static class Data {
        @SerializedName("current_condition")
        public List<CurrentCondition> currentConditionList;

        public class CurrentCondition {
            @SerializedName("cloudcover")
            public String cloudcover;

            @SerializedName("FeelsLikeF")
            public String feelsLikeF;

            @SerializedName("humidity")
            public String humidity;

            @SerializedName("isdaytime")
            public String isdaytime;

            @SerializedName("observation_time")
            public String observationTime;

            @SerializedName("precipMM")
            public String precipMM;

            @SerializedName("pressure")
            public String pressure;

            @SerializedName("temp_F")
            public String tempF;

            @SerializedName("visibility")
            public String visibility;

            @SerializedName("weatherCode")
            public String weatherCode;

            @SerializedName("winddir16Point")
            public String winddir16Point;

            @SerializedName("winddirDegree")
            public String winddirDegree;

            @SerializedName("windspeedKmph")
            public String windspeedKmph;

            public String getCloudcover() {
                return cloudcover;
            }

            public void setCloudcover(String cloudcover) {
                this.cloudcover = cloudcover;
            }

            public String getFeelsLikeF() {
                return feelsLikeF;
            }

            public void setFeelsLikeF(String feelsLikeF) {
                this.feelsLikeF = feelsLikeF;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getIsdaytime() {
                return isdaytime;
            }

            public void setIsdaytime(String isdaytime) {
                this.isdaytime = isdaytime;
            }

            public String getObservationTime() {
                return observationTime;
            }

            public void setObservationTime(String observationTime) {
                this.observationTime = observationTime;
            }

            public String getPrecipMM() {
                return precipMM;
            }

            public void setPrecipMM(String precipMM) {
                this.precipMM = precipMM;
            }

            public String getPressure() {
                return pressure;
            }

            public void setPressure(String pressure) {
                this.pressure = pressure;
            }

            public String getTempF() {
                return tempF;
            }

            public void setTempF(String tempF) {
                this.tempF = tempF;
            }

            public String getVisibility() {
                return visibility;
            }

            public void setVisibility(String visibility) {
                this.visibility = visibility;
            }

            public String getWeatherCode() {
                return weatherCode;
            }

            public void setWeatherCode(String weatherCode) {
                this.weatherCode = weatherCode;
            }

            public String getWinddir16Point() {
                return winddir16Point;
            }

            public void setWinddir16Point(String winddir16Point) {
                this.winddir16Point = winddir16Point;
            }

            public String getWinddirDegree() {
                return winddirDegree;
            }

            public void setWinddirDegree(String winddirDegree) {
                this.winddirDegree = winddirDegree;
            }

            public String getWindspeedKmph() {
                return windspeedKmph;
            }

            public void setWindspeedKmph(String windspeedKmph) {
                this.windspeedKmph = windspeedKmph;
            }
        }

        @SerializedName("nearest_area")
        public List<NearestArea> nearestAreaList;

        public class NearestArea {
            @SerializedName("areaName")
            public List<AreaName> areaNameList;

            public class AreaName {
                @SerializedName("value")
                public String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            @SerializedName("country")
            public List<Country> countryList;

            public class Country {
                @SerializedName("value")
                public String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            @SerializedName("latitude")
            public String latitude;

            @SerializedName("longitude")
            public String longitude;

            @SerializedName("population")
            public String population;

            @SerializedName("weatherUrl")
            public List<WeatherUrl> weatherUrlList;

            public class WeatherUrl {
                @SerializedName("value")
                public String value;
            }

            public List<AreaName> getAreaNameList() {
                return areaNameList;
            }

            public void setAreaNameList(List<AreaName> areaNameList) {
                this.areaNameList = areaNameList;
            }

            public List<Country> getCountryList() {
                return countryList;
            }

            public void setCountryList(List<Country> countryList) {
                this.countryList = countryList;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getPopulation() {
                return population;
            }

            public void setPopulation(String population) {
                this.population = population;
            }

            public List<WeatherUrl> getWeatherUrlList() {
                return weatherUrlList;
            }

            public void setWeatherUrlList(List<WeatherUrl> weatherUrlList) {
                this.weatherUrlList = weatherUrlList;
            }
        }

        @SerializedName("request")
        public List<Request> requestList;

        public class Request {
            @SerializedName("localTime")
            public String localTime;

            @SerializedName("query")
            public String query;

            @SerializedName("type")
            public String type;

            public String getLocalTime() {
                return localTime;
            }

            public void setLocalTime(String localTime) {
                this.localTime = localTime;
            }

            public String getQuery() {
                return query;
            }

            public void setQuery(String query) {
                this.query = query;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        @SerializedName("weather")
        public List<Weather> weatherList;

        public class Weather {
            @SerializedName("astronomy")
            public List<Astronomy> astronomyList;

            public class Astronomy {
                @SerializedName("moonrise")
                public String moonrise;

                @SerializedName("moonset")
                public String moonset;

                @SerializedName("sunrise")
                public String sunrise;

                @SerializedName("sunset")
                public String sunset;

                public String getMoonrise() {
                    return moonrise;
                }

                public void setMoonrise(String moonrise) {
                    this.moonrise = moonrise;
                }

                public String getMoonset() {
                    return moonset;
                }

                public void setMoonset(String moonset) {
                    this.moonset = moonset;
                }

                public String getSunrise() {
                    return sunrise;
                }

                public void setSunrise(String sunrise) {
                    this.sunrise = sunrise;
                }

                public String getSunset() {
                    return sunset;
                }

                public void setSunset(String sunset) {
                    this.sunset = sunset;
                }
            }

            @SerializedName("date")
            public String date;

            @SerializedName("hourly")
            public List<Hourly> hourlyList;

            public class Hourly {
                @SerializedName("chanceoffrost")
                public String chanceoffrost;

                @SerializedName("chanceofovercast")
                public String chanceofovercast;

                @SerializedName("chanceofrain")
                public String chanceofrain;

                @SerializedName("chanceofsnow")
                public String chanceofsnow;

                @SerializedName("chanceofsunshine")
                public String chanceofsunshine;

                @SerializedName("cloudcover")
                public String cloudcover;

                @SerializedName("DewPointF")
                public String dewPointF;

                @SerializedName("FeelsLikeF")
                public String feelsLikeF;

                @SerializedName("HeatIndexF")
                public String heatIndexF;

                @SerializedName("humidity")
                public String humidity;

                @SerializedName("isdaytime")
                public String isdaytime;

                @SerializedName("localHourlyDatetime")
                public String localHourlyDatetime;

                @SerializedName("precipMM")
                public String precipMM;

                @SerializedName("pressure")
                public String pressure;

                @SerializedName("tempF")
                public String tempF;

                @SerializedName("time")
                public String time;

                @SerializedName("UTCdate")
                public String utcDate;

                @SerializedName("UTCtime")
                public String utcTime;

                @SerializedName("visibility")
                public String visibility;

                @SerializedName("weatherCode")
                public String weatherCode;

                @SerializedName("WindChillF")
                public String windChillF;

                @SerializedName("winddir16Point")
                public String windDir16Point;

                @SerializedName("winddirDegree")
                public String windDirDegree;

                @SerializedName("windspeedKmph")
                public String windSpeedKmph;

                public String getChanceoffrost() {
                    return chanceoffrost;
                }

                public void setChanceoffrost(String chanceoffrost) {
                    this.chanceoffrost = chanceoffrost;
                }

                public String getChanceofovercast() {
                    return chanceofovercast;
                }

                public void setChanceofovercast(String chanceofovercast) {
                    this.chanceofovercast = chanceofovercast;
                }

                public String getChanceofrain() {
                    return chanceofrain;
                }

                public void setChanceofrain(String chanceofrain) {
                    this.chanceofrain = chanceofrain;
                }

                public String getChanceofsnow() {
                    return chanceofsnow;
                }

                public void setChanceofsnow(String chanceofsnow) {
                    this.chanceofsnow = chanceofsnow;
                }

                public String getChanceofsunshine() {
                    return chanceofsunshine;
                }

                public void setChanceofsunshine(String chanceofsunshine) {
                    this.chanceofsunshine = chanceofsunshine;
                }

                public String getCloudcover() {
                    return cloudcover;
                }

                public void setCloudcover(String cloudcover) {
                    this.cloudcover = cloudcover;
                }

                public String getDewPointF() {
                    return dewPointF;
                }

                public void setDewPointF(String dewPointF) {
                    this.dewPointF = dewPointF;
                }

                public String getFeelsLikeF() {
                    return feelsLikeF;
                }

                public void setFeelsLikeF(String feelsLikeF) {
                    this.feelsLikeF = feelsLikeF;
                }

                public String getHeatIndexF() {
                    return heatIndexF;
                }

                public void setHeatIndexF(String heatIndexF) {
                    this.heatIndexF = heatIndexF;
                }

                public String getHumidity() {
                    return humidity;
                }

                public void setHumidity(String humidity) {
                    this.humidity = humidity;
                }

                public String getIsdaytime() {
                    return isdaytime;
                }

                public void setIsdaytime(String isdaytime) {
                    this.isdaytime = isdaytime;
                }

                public String getLocalHourlyDatetime() {
                    return localHourlyDatetime;
                }

                public void setLocalHourlyDatetime(String localHourlyDatetime) {
                    this.localHourlyDatetime = localHourlyDatetime;
                }

                public String getPrecipMM() {
                    return precipMM;
                }

                public void setPrecipMM(String precipMM) {
                    this.precipMM = precipMM;
                }

                public String getPressure() {
                    return pressure;
                }

                public void setPressure(String pressure) {
                    this.pressure = pressure;
                }

                public String getTempF() {
                    return tempF;
                }

                public void setTempF(String tempF) {
                    this.tempF = tempF;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getUtcDate() {
                    return utcDate;
                }

                public void setUtcDate(String utcDate) {
                    this.utcDate = utcDate;
                }

                public String getUtcTime() {
                    return utcTime;
                }

                public void setUtcTime(String utcTime) {
                    this.utcTime = utcTime;
                }

                public String getVisibility() {
                    return visibility;
                }

                public void setVisibility(String visibility) {
                    this.visibility = visibility;
                }

                public String getWeatherCode() {
                    return weatherCode;
                }

                public void setWeatherCode(String weatherCode) {
                    this.weatherCode = weatherCode;
                }

                public String getWindChillF() {
                    return windChillF;
                }

                public void setWindChillF(String windChillF) {
                    this.windChillF = windChillF;
                }

                public String getWindDir16Point() {
                    return windDir16Point;
                }

                public void setWindDir16Point(String windDir16Point) {
                    this.windDir16Point = windDir16Point;
                }

                public String getWindDirDegree() {
                    return windDirDegree;
                }

                public void setWindDirDegree(String windDirDegree) {
                    this.windDirDegree = windDirDegree;
                }

                public String getWindSpeedKmph() {
                    return windSpeedKmph;
                }

                public void setWindSpeedKmph(String windSpeedKmph) {
                    this.windSpeedKmph = windSpeedKmph;
                }
            }

            @SerializedName("maxtempF")
            public String maxTempF;

            @SerializedName("mintempF")
            public String minTempF;

            @SerializedName("uvIndex")
            public String uvIndex;

            public List<Astronomy> getAstronomyList() {
                return astronomyList;
            }

            public void setAstronomyList(List<Astronomy> astronomyList) {
                this.astronomyList = astronomyList;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public List<Hourly> getHourlyList() {
                return hourlyList;
            }

            public void setHourlyList(List<Hourly> hourlyList) {
                this.hourlyList = hourlyList;
            }

            public String getMaxTempF() {
                return maxTempF;
            }

            public void setMaxTempF(String maxTempF) {
                this.maxTempF = maxTempF;
            }

            public String getMinTempF() {
                return minTempF;
            }

            public void setMinTempF(String minTempF) {
                this.minTempF = minTempF;
            }

            public String getUvIndex() {
                return uvIndex;
            }

            public void setUvIndex(String uvIndex) {
                this.uvIndex = uvIndex;
            }
        }

        public List<CurrentCondition> getCurrentConditionList() {
            return currentConditionList;
        }

        public void setCurrentConditionList(List<CurrentCondition> currentConditionList) {
            this.currentConditionList = currentConditionList;
        }

        public List<NearestArea> getNearestAreaList() {
            return nearestAreaList;
        }

        public void setNearestAreaList(List<NearestArea> nearestAreaList) {
            this.nearestAreaList = nearestAreaList;
        }

        public List<Request> getRequestList() {
            return requestList;
        }

        public void setRequestList(List<Request> requestList) {
            this.requestList = requestList;
        }

        public List<Weather> getWeatherList() {
            return weatherList;
        }

        public void setWeatherList(List<Weather> weatherList) {
            this.weatherList = weatherList;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
