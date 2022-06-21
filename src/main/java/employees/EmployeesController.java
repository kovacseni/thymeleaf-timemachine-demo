package employees;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/employees")
public class EmployeesController {

    @GetMapping
    public ModelAndView findEmployees() {
        Map<String, Object> model = Map.of("employees",
        List.of(
                new EmployeeDto(1L, "John Doe"),
                new EmployeeDto(2L, "Jack Doe"),
                new EmployeeDto(3L, "Jane Doe")
        ),
                "createEmployeeCommand", new CreateEmployeeCommand("Anonymous"));
        return new ModelAndView("employees", model);
        }

    @PostMapping
    public String createEmployee(@ModelAttribute CreateEmployeeCommand command) {
        log.info("Create employee: {}", command);
        return "redirect:/employees";
    }
}
