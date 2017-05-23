package com.grandcircus.spring.controller;

import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;
import com.uber.sdk.rides.client.model.*;
import com.uber.sdk.rides.client.services.RidesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import retrofit2.Response;


import java.io.IOException;
import java.util.List;


/**
 * Created by broadwells on 5/22/17.
 */


@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String helloworld(Model model) {
        List<Product> results;
        List<PriceEstimate> prices;
        List<TimeEstimate> duration;
        String id = "";

        try {
            SessionConfiguration config = new SessionConfiguration.Builder()
                    .setClientId("clientId")
                    .setServerToken("serverToken")
                    .build();

            ServerTokenSession session = new ServerTokenSession(config);

            UberRidesApi ride = UberRidesApi.with(session).build();
            RidesService service = ride.createService();
            //product
            Response<ProductsResponse> response = service.getProducts(42.335734f, -83.050031f).execute();
            ProductsResponse products = response.body();
            results = products.getProducts();

            //price
            Response<PriceEstimatesResponse> respond = service.getPriceEstimates(42.335734f, -83.050031f,
                    42.462633f, -82.891155f).execute();
            PriceEstimatesResponse priceTag = respond.body();
            prices = priceTag.getPrices();


            //time
            Response<TimeEstimatesResponse> responseTime = service.getPickupTimeEstimate(42.335734f, -83.050031f,
                    id).execute();

            TimeEstimatesResponse time = responseTime.body();

            duration = time.getTimes();

            String displayName = results.get(0).getDisplayName();
            String discript = results.get(0).getDescription();
            int cap = results.get(0).getCapacity();

            String priceEst = prices.get(0).getEstimate() + " " + prices.get(0).getCurrencyCode();
            Float distance = prices.get(0).getDistance();

            int seconds = duration.get(0).getEstimate();
            int eta = (seconds%3600)/60;

            model.addAttribute("product", displayName);
            model.addAttribute("descrip", discript);
            model.addAttribute("cap", cap);
            model.addAttribute("price", priceEst);
            model.addAttribute("mile", distance);
            model.addAttribute("time", eta);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return "welcome";

    }
}
