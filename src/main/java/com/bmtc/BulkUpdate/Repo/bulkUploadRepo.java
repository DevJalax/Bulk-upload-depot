package com.bmtc.BulkUpdate.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmtc.BulkUpdate.entity.BulkDepot;

public interface bulkUploadRepo extends JpaRepository<BulkDepot,Long> {

}
