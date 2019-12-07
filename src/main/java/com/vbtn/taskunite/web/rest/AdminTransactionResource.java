package com.vbtn.taskunite.web.rest;

import com.vbtn.taskunite.service.AdminTransactionService;
import com.vbtn.taskunite.web.rest.errors.BadRequestAlertException;
import com.vbtn.taskunite.service.dto.AdminTransactionDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.vbtn.taskunite.domain.AdminTransaction}.
 */
@RestController
@RequestMapping("/api")
public class AdminTransactionResource {

    private final Logger log = LoggerFactory.getLogger(AdminTransactionResource.class);

    private static final String ENTITY_NAME = "adminTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdminTransactionService adminTransactionService;

    public AdminTransactionResource(AdminTransactionService adminTransactionService) {
        this.adminTransactionService = adminTransactionService;
    }

    /**
     * {@code POST  /admin-transactions} : Create a new adminTransaction.
     *
     * @param adminTransactionDTO the adminTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adminTransactionDTO, or with status {@code 400 (Bad Request)} if the adminTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin-transactions")
    public ResponseEntity<AdminTransactionDTO> createAdminTransaction(@RequestBody AdminTransactionDTO adminTransactionDTO) throws URISyntaxException {
        log.debug("REST request to save AdminTransaction : {}", adminTransactionDTO);
        if (adminTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new adminTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdminTransactionDTO result = adminTransactionService.save(adminTransactionDTO);
        return ResponseEntity.created(new URI("/api/admin-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin-transactions} : Updates an existing adminTransaction.
     *
     * @param adminTransactionDTO the adminTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adminTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the adminTransactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adminTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin-transactions")
    public ResponseEntity<AdminTransactionDTO> updateAdminTransaction(@RequestBody AdminTransactionDTO adminTransactionDTO) throws URISyntaxException {
        log.debug("REST request to update AdminTransaction : {}", adminTransactionDTO);
        if (adminTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdminTransactionDTO result = adminTransactionService.save(adminTransactionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adminTransactionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /admin-transactions} : get all the adminTransactions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adminTransactions in body.
     */
    @GetMapping("/admin-transactions")
    public ResponseEntity<List<AdminTransactionDTO>> getAllAdminTransactions(Pageable pageable) {
        log.debug("REST request to get a page of AdminTransactions");
        Page<AdminTransactionDTO> page = adminTransactionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /admin-transactions/:id} : get the "id" adminTransaction.
     *
     * @param id the id of the adminTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adminTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admin-transactions/{id}")
    public ResponseEntity<AdminTransactionDTO> getAdminTransaction(@PathVariable Long id) {
        log.debug("REST request to get AdminTransaction : {}", id);
        Optional<AdminTransactionDTO> adminTransactionDTO = adminTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adminTransactionDTO);
    }

    /**
     * {@code DELETE  /admin-transactions/:id} : delete the "id" adminTransaction.
     *
     * @param id the id of the adminTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin-transactions/{id}")
    public ResponseEntity<Void> deleteAdminTransaction(@PathVariable Long id) {
        log.debug("REST request to delete AdminTransaction : {}", id);
        adminTransactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
