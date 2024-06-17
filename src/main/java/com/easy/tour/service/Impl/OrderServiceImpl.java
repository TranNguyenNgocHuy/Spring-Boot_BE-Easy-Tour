package com.easy.tour.service.Impl;


import com.easy.tour.dto.OrderDTO;
import com.easy.tour.entity.Order.Order;
import com.easy.tour.entity.Tour.Tour;
import com.easy.tour.entity.departure.DepartureDate;
import com.easy.tour.mapper.DepartureDateMapper;
import com.easy.tour.mapper.OrderMapper;
import com.easy.tour.repository.DepartureDateRepository;
import com.easy.tour.repository.OrderRepository;
import com.easy.tour.repository.TourRepository;
import com.easy.tour.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl extends AbstractBaseServiceImpl<OrderDTO> implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DepartureDateRepository departureDateRepository;

    @Autowired
    DepartureDateMapper departureDateMapper;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    OrderMapper orderMapper;

    public OrderServiceImpl() {
        super.setMapper(new OrderMapper());
    }

    @Override
    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(orderRepository);
    }

    @Override
    public boolean customUpdateById(OrderDTO orderDto, Integer id) {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(id);
            if (optionalOrder.isPresent()) {
                Order retrievedOrder = optionalOrder.get();
                orderMapper.convertEntityToDTO(retrievedOrder);

                DepartureDate departureDate = retrievedOrder.getDepartureDate();
                if(departureDate != null) {
                    departureDate.setDepartureDate(orderDto.getBookingDate());
                } else {
                    departureDate = new DepartureDate();
                    departureDate.setDepartureDate(orderDto.getBookingDate());
                    retrievedOrder.setDepartureDate(departureDate);
                }
                orderRepository.save(retrievedOrder);
                return true;
            } else {
                log.error("Order with id " + id + " not found.");
                return false;
            }
        } catch (Exception ex) {
            log.error("Error when updating order: " + ex.getMessage(), ex);
            return false;
        }

//        try {
//            Order retrievedOrder = orderRepository.findById(id).get();
//            orderMapper.mapDTOToEntity(orderDto, retrievedOrder);
//            // manually set departureDate of the DepartureDate in the Order.
//            retrievedOrder.getDepartureDate().setDepartureDate(orderDto.getBookingDate());
//            orderRepository.save(retrievedOrder);
//            return true;
//        } catch (Exception ex) {
//            log.error("Error when update tour: " + ex);
//            return false;
//        }
    }

    @Override
    public OrderDTO customCreate(OrderDTO orderDTO) {
        System.out.println("lalafq" + orderDTO.getBookingDate());
        Order order = orderMapper.convertDTOToEntity(orderDTO);

        LocalDate bookingDate = orderDTO.getBookingDate();
        Tour retrievedTour = tourRepository.findByTourCode(orderDTO.getTourCode());
        for (DepartureDate dateItem : retrievedTour.getDepartureDateList()){
            if (dateItem.getDepartureDate().equals(bookingDate)) {
                order.setDepartureDate(dateItem);
            }
        }

//        Tour retrievedTour = tourRepository.findByTourCode(orderDTO.getTourCode());
//        LocalDate date = LocalDate.of(2023, 5, 11); // Example date
//
//        // add DepartureDate into Tour by using tourCode to create DepartureDate
//        DepartureDateDTO dto = new DepartureDateDTO(orderDTO.getTourCode(), date);
//        DepartureDate sampleBookingDate = departureDateMapper.convertDTOToEntity(dto);
//
//        // add references into 2 tables
//        sampleBookingDate.setTour(retrievedTour);
//        retrievedTour.addDepartureDate(sampleBookingDate);
//
//        // set order's departure date
//        order.setDepartureDate(sampleBookingDate);

        OrderDTO result = orderMapper.convertEntityToDTO(orderRepository.save(order));
        result.setTourCode(order.getDepartureDate().getTour().getTourCode());
        result.setBookingDate(order.getDepartureDate().getDepartureDate());
        return result;
    }


    @Override
    public OrderDTO customGetById(Integer id) {
        Order order = orderRepository.findById(id).get();
        OrderDTO result = orderMapper.convertEntityToDTO(order);
        result.setTourCode(order.getDepartureDate().getTour().getTourCode());
        result.setBookingDate(order.getDepartureDate().getDepartureDate());
        return result;
    }
}
