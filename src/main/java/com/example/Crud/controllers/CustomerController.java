package com.example.Crud.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.Crud.model.Customer;
import com.example.Crud.repos.CustomerRepo;
import com.example.Crud.model.Sensors;
import com.example.Crud.repos.SensorRepo;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
@Controller
public class CustomerController {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private SensorRepo sensorRepo;

    @GetMapping("/create")
    public String create(Model model) {
       Map<Integer, String> appType = new HashMap<>();
        appType.put(1, "Type 1");
        appType.put(2, "Type 2");
        
        Map<Integer, String> customer = new HashMap<>();
        customer.put(1, "Customer 1");
        customer.put(2, "Customer 2");
        
        Map<Integer, String> sensorType = new HashMap<>();
        sensorType.put(1, "Sensor 1");
        sensorType.put(2, "sensor 2");
        
        Map<Integer, String> units = new HashMap<>();
        units.put(1, "Length");
        units.put(2, "Feet");
        
        Map<Integer, String> msgFormat = new HashMap<>();
        msgFormat.put(1, "Text");
        msgFormat.put(2, "Content");
        
        // Add the maps to the model
        model.addAttribute("appType", appType);
        model.addAttribute("customer", customer);
        model.addAttribute("sensorType", sensorType);
        model.addAttribute("units", units);
        model.addAttribute("msgFormat", msgFormat);
        return "create";
    }
    @GetMapping("/index")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerRepo.findAll(); 
        model.addAttribute("customers", customers); 
        return "index";
    }   
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
       Map<Integer, String> appType = new HashMap<>();
        appType.put(1, "Type 1");
        appType.put(2, "Type 2");
        
        Map<Integer, String> customer = new HashMap<>();
        customer.put(1, "Customer 1");
        customer.put(2, "Customer 2");
        
        Map<Integer, String> sensorType = new HashMap<>();
        sensorType.put(1, "Sensor 1");
        sensorType.put(2, "sensor 2");
        
        Map<Integer, String> units = new HashMap<>();
        units.put(1, "Length");
        units.put(2, "Feet");
        
        Map<Integer, String> msgFormat = new HashMap<>();
        msgFormat.put(1, "Text");
        msgFormat.put(2, "Content");
        Customer existingCustomer = customerRepo.findById(id).orElse(null);
        List<Sensors> existingSensors = sensorRepo.findByCustomerId(id.intValue());
        model.addAttribute("id",id);
        model.addAttribute("sensor_count",existingSensors.size());
        model.addAttribute("data", existingCustomer);
        model.addAttribute("sens", existingSensors);
        model.addAttribute("appType", appType);
        model.addAttribute("customer", customer);
        model.addAttribute("sensorType", sensorType);
        model.addAttribute("units", units);
        model.addAttribute("msgFormat", msgFormat);
        return "edit";
    }  
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(
            @RequestParam String configId,
            @RequestParam String customerName,
            @RequestParam int applicationType,
            @RequestParam String onboardingDate,
            @RequestParam String effectiveDate,
            @RequestParam String[] sensorName,
            @RequestParam Integer[] sensorType,
            @RequestParam(value = "inpOrOut", required = false, defaultValue = "false") String[] inpOrOut,
            @RequestParam Integer[] units,
            @RequestParam(required = false) Integer[] msgFormat,
            @RequestParam String[] fromVal,
            @RequestParam String[] toVal,
            HttpServletRequest request) {            
        Customer newCustomer =new Customer();        
        newCustomer.setConfigId(configId);
        newCustomer.setCustomerName(customerName);
        newCustomer.setApplicationType(applicationType);
        newCustomer.setOnboardingDate(onboardingDate); 
        newCustomer.setEffectiveDate(effectiveDate); 
        
        Customer savedCustomer = customerRepo.save(newCustomer);
        int customerID = savedCustomer.getId().intValue();       
        for (int i = 0; i < sensorName.length; i++) {
            Sensors newSensors= new Sensors();
            newSensors.setCustomerId(customerID);
            newSensors.setSensor_name(sensorName[i]);
            newSensors.setSensor_type(sensorType[i]);
            newSensors.setInp_or_out(inpOrOut != null && i < inpOrOut.length ? Boolean.parseBoolean(inpOrOut[i]) : false); 
            newSensors.setUnits(units[i]);
            newSensors.setMessage_format(msgFormat != null && i < msgFormat.length ? msgFormat[i] : 0);
            newSensors.setFrom_val(fromVal[i]);
            newSensors.setTo_val(toVal[i]);
            sensorRepo.save(newSensors);
        }        
        return "redirect:/index"; 
    }    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @RequestParam String configId,
            @RequestParam String customerName,
            @RequestParam int applicationType,
            @RequestParam String onboardingDate,
            @RequestParam String effectiveDate,
            @RequestParam String[] sensorName,
            @RequestParam Integer[] sensorType,
            @RequestParam(required = false,defaultValue = "null") Long[] sensorId,
            @RequestParam(value = "inpOrOut", required = false, defaultValue = "false") String[] inpOrOut,
            @RequestParam Integer[] units,
            @RequestParam(required = false) Integer[] msgFormat,
            @RequestParam String[] fromVal,
            @RequestParam(required = false,defaultValue = "null") Integer id,
            @RequestParam String[] toVal,
            HttpServletRequest request) {            
        Customer newCustomer = (id != null) ? customerRepo.findById(id.longValue()).orElse(new Customer()) : new Customer();        
        newCustomer.setConfigId(configId);
        newCustomer.setCustomerName(customerName);
        newCustomer.setApplicationType(applicationType);
        newCustomer.setOnboardingDate(onboardingDate); 
        newCustomer.setEffectiveDate(effectiveDate); 
        
        Customer savedCustomer = customerRepo.save(newCustomer);
        int customerID = savedCustomer.getId().intValue();       
        for (int i = 0; i < sensorName.length; i++) {
            Sensors newSensors;
            if (sensorId[i] != null) {
                newSensors = sensorRepo.findById(sensorId[i]).orElse(new Sensors());
            } else {
                newSensors = new Sensors();
            }
            newSensors.setCustomerId(customerID);
            newSensors.setSensor_name(sensorName[i]);
            newSensors.setSensor_type(sensorType[i]);
            newSensors.setInp_or_out(inpOrOut != null && i < inpOrOut.length ? Boolean.parseBoolean(inpOrOut[i]) : false); 
            newSensors.setUnits(units[i]);
            newSensors.setMessage_format(msgFormat != null && i < msgFormat.length ? msgFormat[i] : 0);
            newSensors.setFrom_val(fromVal[i]);
            newSensors.setTo_val(toVal[i]);
            sensorRepo.save(newSensors);
        }        
        return "redirect:/index"; 
    }

    // @PostMapping("/update/{id}")
    // public String update(
    //     @RequestParam(required = false) String configId,
    //     @RequestParam(required = false) String customerName,
    //     @RequestParam int applicationType,
    //     @RequestParam String onboardingDate,
    //     @RequestParam String effectiveDate,
    //     @RequestParam String[] sensorName,
    //     @RequestParam Integer[] sensorType,
    //     @RequestParam(value = "inpOrOut", required = false, defaultValue = "0") String[] inpOrOut,
    //     @RequestParam int[] units,
    //     @RequestParam(required = false) int[] msgFormat,
    //     @RequestParam String[] fromVal,
    //     @RequestParam String[] toVal,
    //     HttpServletRequest request,
    //     Model model,@PathVariable Long id) {
    //      Customer existingCustomer = customerRepo.findById(id).orElse(null);
    //      if (existingCustomer == null) {
    //          model.addAttribute("error", "Customer not found.");
    //          return "redirect:/index";
    //      }        
    //      existingCustomer.setConfigId(configId);
    //      existingCustomer.setCustomerName(customerName);
    //      existingCustomer.setApplicationType(applicationType);
    //      existingCustomer.setOnboardingDate(onboardingDate); 
    //      existingCustomer.setEffectiveDate(effectiveDate); 
    //      customerRepo.save(existingCustomer);
    //      int customerID=existingCustomer.getId().intValue();
    //      sensorRepo.deleteByCustomerId(customerID);
    //      for (int i = 0; i < sensorName.length; i++) {
    //         Sensors newSensors = new Sensors();
    //         newSensors.setCustomerId(customerID);
    //         newSensors.setSensor_name(sensorName[i]);
    //         newSensors.setSensor_type(sensorType[i]);
    //         newSensors.setInp_or_out(inpOrOut[i]!=null?true:false); 
    //         newSensors.setUnits(units[i]);
    //         newSensors.setMessage_format(msgFormat[i]);
    //         newSensors.setFrom_val(fromVal[i]);
    //         newSensors.setTo_val(toVal[i]);
    //         sensorRepo.save(newSensors);
    //     }

    //      return "redirect:/index";
    // }   
}
