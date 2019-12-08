package com.vbtn.taskunite.web.rest;

import com.vbtn.taskunite.service.AdminProfitService;
import com.vbtn.taskunite.web.rest.errors.BadRequestAlertException;
import com.vbtn.taskunite.service.dto.AdminProfitDTO;

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
 * REST controller for managing {@link com.vbtn.taskunite.domain.AdminProfit}.
 */
@RestController
@RequestMapping("/api")
public class AdminProfitResource {

    private final Logger log = LoggerFactory.getLogger(AdminProfitResource.class);

    private static final String ENTITY_NAME = "adminProfit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdminProfitService adminProfitService;

    public AdminProfitResource(AdminProfitService adminProfitService) {
        this.adminProfitService = adminProfitService;
    }

    /**
     * {@code POST  /admin-profits} : Create a new adminProfit.
     *
     * @param adminProfitDTO the adminProfitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adminProfitDTO, or with status {@code 400 (Bad Request)} if the adminProfit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin-profits")
    public ResponseEntity<AdminProfitDTO> createAdminProfit(@RequestBody AdminProfitDTO adminProfitDTO) throws URISyntaxException {
        log.debug("REST request to save AdminProfit : {}", adminProfitDTO);
        if (adminProfitDTO.getId() != null) {
            throw new BadRequestAlertException("A new adminProfit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdminProfitDTO result = adminProfitService.save(adminProfitDTO);
        return ResponseEntity.created(new URI("/api/admin-profits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin-profits} : Updates an existing adminProfit.
     *
     * @param adminProfitDTO the adminProfitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adminProfitDTO,
     * or with status {@code 400 (Bad Request)} if the adminProfitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adminProfitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin-profits")
    public ResponseEntity<AdminProfitDTO> updateAdminProfit(@RequestBody AdminProfitDTO adminProfitDTO) throws URISyntaxException {
        log.debug("REST request to update AdminProfit : {}", adminProfitDTO);
        if (adminProfitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdminProfitDTO result = adminProfitService.save(adminProfitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adminProfitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /admin-profits} : get all the adminProfits.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adminProfits in body.
     */
    @GetMapping("/admin-profits")
    public ResponseEntity<List<AdminProfitDTO>> getAllAdminProfits(Pageable pageable) {
        log.debug("REST request to get a page of AdminProfits");
        Page<AdminProfitDTO> page = adminProfitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /admin-profits/:id} : get the "id" adminProfit.
     *
     * @param id the id of the adminProfitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adminProfitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admin-profits/{id}")
    public ResponseEntity<AdminProfitDTO> getAdminProfit(@PathVariable Long id) {
        log.debug("REST request to get AdminProfit : {}", id);
        Optional<AdminProfitDTO> adminProfitDTO = adminProfitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adminProfitDTO);
    }

    /**
     * {@code DELETE  /admin-profits/:id} : delete the "id" adminProfit.
     *
     * @param id the id of the adminProfitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin-profits/{id}")
    public ResponseEntity<Void> deleteAdminProfit(@PathVariable Long id) {
        log.debug("REST request to delete AdminProfit : {}", id);
        adminProfitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
