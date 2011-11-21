package com.trifork.lessor100.datastore;

import com.google.appengine.api.datastore.DatastoreService;

public interface TransactionCallback {
    void doInTransaction();
}
