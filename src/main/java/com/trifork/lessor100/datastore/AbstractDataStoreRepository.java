package com.trifork.lessor100.datastore;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public abstract class AbstractDataStoreRepository {

    protected void doDataStoreOperation(TransactionCallback transactionCallback) {
        DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
        Transaction transaction = dataStore.beginTransaction();
        try {
            transactionCallback.doInTransaction();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    protected List<Entity> runQuery(Query query) {
        DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery preparedQuery = dataStore.prepare(query);
        return preparedQuery.asList(FetchOptions.Builder.withDefaults());
    }

}
