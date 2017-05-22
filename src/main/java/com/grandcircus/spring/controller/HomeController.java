package com.grandcircus.spring.controller;

import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;

import com.uber.sdk.rides.client.model.*;
import com.uber.sdk.rides.client.services.RidesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import retrofit2.Response;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by broadwells on 5/22/17.
 */


@Controller
public class HomeController {

    @RequestMapping("/")
    public String helloworld(Model model){
        List<Product> results;
        List<PriceEstimate> prices;
        try {
            SessionConfiguration config = new SessionConfiguration.Builder()
                    .setClientId("clientId")
                    .setServerToken("serverToken")
                    .build();

            ServerTokenSession session = new ServerTokenSession(config);

            UberRidesApi ride = UberRidesApi.with(session).build();
            RidesService service = ride.createService();
            Response<ProductsResponse> response = service.getProducts(42.335734f, -83.050031f).execute();
            ProductsResponse products = response.body();
            results = products.getProducts();

            Response<PriceEstimatesResponse> respond = service.getPriceEstimates(42.335734f, -83.050031f,
                    42.352450f, -83.061618f).execute();
            PriceEstimatesResponse priceTag = respond.body();
            prices = priceTag.getPrices();

            model.addAttribute("price", prices);
            model.addAttribute("product", results);

//            for (PriceEstimate i: prices) {
//
//                System.out.println(i.getEstimate());
//            }
//            for (Product j : results){
//                System.out.println(j.getDisplayName());
//            }

        }catch (IOException e){
            
        }


        return "welcome";
    }

}
