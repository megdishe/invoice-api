package com.epicraft.pdp.invoice.controller;

import com.epicraft.pdp.invoice.domain.Invoice;
import com.epicraft.pdp.invoice.dto.CreateInvoiceRequest;
import com.epicraft.pdp.invoice.repository.InvoiceRepository;
import com.epicraft.pdp.invoice.service.InvoiceService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final InvoiceRepository invoiceRepository;
    public InvoiceController(InvoiceService invoiceService, InvoiceRepository invoiceRepository) { this.invoiceService = invoiceService; this.invoiceRepository = invoiceRepository; }
    @PostMapping public Invoice create(@RequestBody CreateInvoiceRequest request) { return invoiceService.generate(request); }
    @GetMapping public List<Invoice> findAll() { return invoiceRepository.findAll(); }

    @PostMapping("/temporary/legacy")
    public Invoice recreateLegacyInvoice(@RequestParam int month, @RequestParam int year, @RequestParam int numberOfDays) {
        return invoiceService.generateLegacy(month, year, numberOfDays);
    }

    @GetMapping("/{id}") public Invoice findById(@PathVariable String id) { return invoiceRepository.findById(id).orElseThrow(); }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<Resource> downloadPdf(@PathVariable String id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        if (invoice.pdfPath() == null || invoice.pdfPath().isBlank()) {
            throw new IllegalStateException("No PDF is attached to invoice " + id);
        }

        Path pdfPath = Path.of(invoice.pdfPath()).normalize().toAbsolutePath();
        FileSystemResource resource = new FileSystemResource(pdfPath.toFile());
        if (!resource.exists() || !resource.isReadable()) {
            throw new IllegalStateException("PDF file not found for invoice " + id);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.attachment()
                        .filename(invoice.invoiceNumber() + ".pdf")
                        .build()
        );
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
