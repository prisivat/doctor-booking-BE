package com.example.Ticket.Booking.service;

import com.example.Ticket.Booking.dao.HospitalDetailsRepository;
import com.example.Ticket.Booking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HospitalDetailsServiceImpl implements HospitalDetailsService{


    @Autowired
    HospitalDetailsRepository hospitalDetailsRepository;


    @Override
    public Map<String,List<HospitalDtl>> getHsptlDtls(List<String> location) {

        List<HospitalDetails>  detailsList = hospitalDetailsRepository.findByLocationInOrderByLocationAsc((location));
        Map<String,List<HospitalDtl>> listOfHospDtls = new LinkedHashMap<>();
         detailsList.stream().forEach(hospitalDetails -> {
             listOfHospDtls.put(hospitalDetails.getLocation(), hospitalDetails.getHospitalDetails());

         });

        return listOfHospDtls;
    }

    @Override
    public Map<String, Set<String>> getListOfSpclName(List<String> location) {

        List<HospitalDetails>  detailsList = hospitalDetailsRepository.findByLocationInOrderByLocationAsc(location);

        Map<String, Set<String>> specalistName = new LinkedHashMap<>();
        detailsList.stream().forEach(hospitalDetails -> {
            Set<String> specalist = hospitalDetails.getHospitalDetails().stream().map(i -> i.getSpecalist()).flatMap(i -> i.stream().map(j -> j.getSpclName())).collect(Collectors.toSet());
            specalistName.put(hospitalDetails.getLocation(), specalist);
        });

        return specalistName;
    }

    @Override
    public List<HospitalDtlBySpclty> getSpecalistDetails(SpecalistName spclList) {
        HospitalDetails  detailsList = hospitalDetailsRepository.findByLocation(spclList.getLocation());
        List<HospitalDtlBySpclty> hospitalDtlBySpclties = new ArrayList<>();

        detailsList.getHospitalDetails().stream().forEach(i -> {
            String docName = i.getName();
            List<Specalist> val = i.getSpecalist().stream().filter(j -> spclList.getSpecalistName().contains(j.getSpclName())).collect(Collectors.toList());
            HospitalDtlBySpclty hospitalDtlBySpclty = new HospitalDtlBySpclty();
            if(val.size() > 0){
           hospitalDtlBySpclty.setHospitalName(docName);
           hospitalDtlBySpclty.setSpecalists(val);
           hospitalDtlBySpclties.add(hospitalDtlBySpclty);
       }
        });
        return hospitalDtlBySpclties;
    }


}
