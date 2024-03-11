package com.example.qa_testers.controller;

import com.example.qa_testers.dto.request.UpsertTestCaseDTO;
import com.example.qa_testers.dto.response.MessageDTO;
import com.example.qa_testers.dto.response.TestCaseDTO;
import com.example.qa_testers.service.ITestCaseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {
    private final ITestCaseService testCaseService;

    public TestCaseController(ITestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @PostMapping("/new")
    public ResponseEntity<TestCaseDTO> createTestCase(
        @Valid @RequestBody UpsertTestCaseDTO createTestCaseDTO
    ) {
        return new ResponseEntity<TestCaseDTO>(testCaseService.create(createTestCaseDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TestCaseDTO>> getAllTestCases(
            @RequestParam(required = false)
            @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", message = "El formato de la fecha debe ser dd/MM/yyyy")
            String date
    ) {
        return new ResponseEntity<List<TestCaseDTO>>(testCaseService.getAll(date), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestCaseDTO> getTestCaseById(
        @PathVariable Long id
    ) {
        return new ResponseEntity<TestCaseDTO>(testCaseService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestCaseDTO> updateTestCase(
        @PathVariable Long id,
        @Valid @RequestBody UpsertTestCaseDTO updateTestCaseDTO
    ) {
        return new ResponseEntity<TestCaseDTO>(testCaseService.update(id, updateTestCaseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> deleteTestCase(
        @PathVariable Long id
    ) {
        return new ResponseEntity<MessageDTO>(testCaseService.delete(id), HttpStatus.OK);
    }
}