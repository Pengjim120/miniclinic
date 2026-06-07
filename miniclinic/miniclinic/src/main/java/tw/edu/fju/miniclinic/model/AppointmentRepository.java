package tw.edu.fju.miniclinic.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByApptDate(LocalDate apptDate);
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByPatient(Patient patient);
    long countByApptDateBetween(LocalDate from, LocalDate to);
    
    // 新增：依據狀態統計掛號筆數
    long countByStatus(String status);
    List<Appointment> findByDoctorAndApptDate(Doctor doctor, LocalDate today);

    /**
     * 統計各科別的掛號總數
     * 使用 JPQL 進行關聯查詢 (Appointment -> Doctor) 並分組統計
     */
    @Query("SELECT d.department, COUNT(a) FROM Appointment a JOIN a.doctor d GROUP BY d.department")
    List<Object[]> countAppointmentsByDepartment();
}