package team6.java.ca.serviceImpls;

import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.java.ca.entities.EmployeeLeaveRecord;
import team6.java.ca.repositories.PublicHolidayRepository;
import team6.java.ca.services.LeaveUtilService;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class LeaveUtilServiceImpl implements LeaveUtilService {

    @Autowired
    private PublicHolidayRepository publicHolidayRepository;
    
    @Override
    public double calculateActualLeaveDays(EmployeeLeaveRecord leaveRecord) {
        LocalDate startDate = leaveRecord.getLeaveDate();
        LocalDate endDate = leaveRecord.getEndDate();

        // Total days inclusive of start and end dates
        double totalDays = (double) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        
        if (leaveRecord.isHalfDay()) {
            totalDays -= 0.5;
        }

        // If the leave period is 14 days or less, exclude weekends and public holidays
        if (totalDays <= 14) {
            int weekendDays = getNumberOfWeekendDaysInLeaveRange(startDate, endDate);
            int publicHolidays = publicHolidayRepository.findAllByDateBetween(startDate, endDate).size();
            totalDays -= (weekendDays + publicHolidays);
        }

        // Return the actual leave days
        return totalDays;
    }
    
    @Override
    public int getNumberOfWeekendDaysInLeaveRange(LocalDate startDate, LocalDate endDate) {
        int count = 0;
        LocalDate date = startDate;
        
        while (!date.isAfter(endDate)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                count++;
            }
            date = date.plusDays(1);
        }
        return count;
    }
    
   
}

