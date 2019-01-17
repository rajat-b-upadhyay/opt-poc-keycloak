package optpoc.keycloack.stockservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@RestController
public class StockController {

	@Autowired
	private StockRepository stockRepository = null;
	
	protected Logger log = LoggerFactory.getLogger(StockController.class.getSimpleName());
	
    @GetMapping("/stocks")
    public List<Stock> getStocks() {
        log.info("Inside Stocks");
        return stockRepository.findAll();
    }

    @GetMapping("/stocks/{id}")
    public Stock getStocksById(@PathVariable("id") Long id) {
    	
        return stockRepository.findOne(id);
    }

}
