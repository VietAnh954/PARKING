package vn.bacon.parking.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.bacon.parking.domain.MonthlyRegistrationRequest;
import vn.bacon.parking.domain.Student;
import vn.bacon.parking.domain.Vehicle;
import vn.bacon.parking.repository.MonthlyRegistrationRequestRepository;
import vn.bacon.parking.repository.StudentRepository;
import vn.bacon.parking.repository.VehicleRepository;

@Service
public class MonthlyRegistrationRequestService {

    @Autowired
    private MonthlyRegistrationRequestRepository requestRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public MonthlyRegistrationRequest createRequest(MonthlyRegistrationRequest request) {
        if (request.getStudent() == null || request.getVehicle() == null) {
            throw new IllegalArgumentException("Student and Vehicle are required.");
        }
        return requestRepository.save(request);
    }

    public boolean hasActiveRequestForVehicle(String bienSoXe) {
        return requestRepository.existsByVehicleBienSoXeAndStatusNot(bienSoXe, "Từ chối");
    }

    public boolean hasActiveRequestForVehicleExcludingRequestId(String vehicleId, String requestId) {
        return requestRepository.existsByVehicleBienSoXeAndRequestIdNotAndStatusIn(
                vehicleId, requestId, Arrays.asList("Chờ duyệt", "Đã duyệt"));
    }

    public List<MonthlyRegistrationRequest> getRequestsByStudentId(String studentId) {
        return requestRepository.findByStudentMaSV(studentId);
    }

    public Optional<MonthlyRegistrationRequest> getRequestById(String requestId) {
        return requestRepository.findById(requestId);
    }

    public Long getMaxRequestId() {
        return requestRepository.findMaxRequestId();
    }

    @Transactional
    public MonthlyRegistrationRequest updateRequest(MonthlyRegistrationRequest request) {
        if (request.getRequestId() == null) {
            throw new IllegalArgumentException("Request ID cannot be null for update.");
        }
        return requestRepository.save(request);
    }

    public Page<MonthlyRegistrationRequest> getAllRequests(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

}