package com.musala.dronesserver.controller;

import com.musala.dronesdto.dto.MedicationRequestDto;
import com.musala.dronesdto.dto.MedicationResponseShortDto;
import com.musala.dronesserver.model.MedicationFile;
import com.musala.dronesserver.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(path = "/medication")
@RequiredArgsConstructor
@Validated
public class MedicationController {
    private final MedicationService medicationService;

    @GetMapping
    public List<MedicationResponseShortDto> getMedications() {
        return medicationService.getMedications();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicationResponseShortDto createMedication(@RequestBody @Validated MedicationRequestDto medicationRequestDto) {
        return medicationService.createMedication(medicationRequestDto);
    }

    @PostMapping(value = "/{id}/uploadFile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@PathVariable("id") Long medicationId, @RequestPart MultipartFile file) throws IOException {
        medicationService.saveFile(medicationId, file);
    }

    @GetMapping("/{id}/downloadFile")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long medicationId) {
        MedicationFile medicationFile = medicationService.downloadFile(medicationId);
        InputStream inputStream = new ByteArrayInputStream(medicationFile.getBinaryData());
        InputStreamResource resource = new InputStreamResource(inputStream);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(medicationFile.getContentType()))
                .contentLength(medicationFile.getSize())
                .header("Content-Disposition", "attachment; filename="
                        + medicationFile.getOriginalFilename())
                .body(resource);
    }

    @PostMapping("/{id}/deleteFile")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(@PathVariable("id") Long medicationId) {
        medicationService.deletePicture(medicationId);
    }
}
