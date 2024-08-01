package team6.java.ca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.java.ca.entities.EmployeeLeaveRecord;
import team6.java.ca.repositories.EmployeeLeaveRecordRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CSVGenerationService {

    @Autowired
    private EmployeeLeaveRecordRepository employeeLeaveRecordRepository;

    public void generateCSVForCurrentLeaveEmployees(String filePath) throws IOException {
        List<EmployeeLeaveRecord> leaveRecords = employeeLeaveRecordRepository.findAllCurrentLeaveRecords();
        writeCSV(leaveRecords, filePath);
    }

    public void generateCSVForLeaveRecordsUnderManager(Long managerId, String filePath) throws IOException {
        List<EmployeeLeaveRecord> leaveRecords = employeeLeaveRecordRepository.findAllCurrentLeaveRecordsUnderManager(managerId);
        writeCSV(leaveRecords, filePath);
    }

    private void writeCSV(List<EmployeeLeaveRecord> records, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("LeaveID,EmployeeID,LeaveDate,LeaveType,Status,ManagerID\n");
            for (EmployeeLeaveRecord record : records) {
                writer.write(record.getLeaveId() + ",");
                writer.write(record.getEmployee().getUserId() + ",");
                writer.write(record.getLeaveDate() + ",");
                writer.write(record.getLeaveType().getLeaveTypeName() + ",");
                writer.write(record.getStatus() + ",");
                writer.write(record.getApproveManager().getUserId() + "\n");
            }
        }
    }
}
