package optpoc.keycloack.stockservice;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
class StockRepository {

    private static List<Stock> stocks = Arrays.asList(
            new Stock(1L,"Twitter Inc.","10.00","100"),
            new Stock(2L,"Facebook Inc.","50.00","500"),
            new Stock(3L,"Google Inc.","40.10","200"),
            new Stock(4L,"Oracle Corp.","100.00","250"),
            new Stock(5L,"Netflix Inc.","100.40","120")
    );

    List<Stock> findAll() {
        return stocks;
    }

    Stock findOne(Long id) {
        return stocks.stream().filter(customer -> customer.getId().equals(id)).findFirst().get();
    }
}
