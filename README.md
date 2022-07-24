# Baraka assigment


# Getting Started

How to use with docker:

```sh
docker build -t candle-service .
docker run -d -p 9989:9989 -p 8080:8080 candle-service
```

REST API : GET /<stock_name>/candles

## Candle creation interval change
 change values in application.yaml (supported only minutes and hours)
```yaml
candle:
  unit:
    type: minutes
    count: 1
```
for update with new intervals, please go to
```
com.palahno.candleservice.configuration.CandleConfiguration
```

and add new values to

```java
  private final Map<String, ChronoField> candleUnits = new HashMap<>() {{
        put("minutes", MINUTE_OF_DAY);
        put("hours", HOUR_OF_DAY);
    }};
 ```

