package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import com.example.demo.Model.Customer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CustomerService {

	private static final String COLLECTION_NAME = "Customers";

	public String saveCustomer(Customer customer) throws Exception, Exception {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuter = dbFirestore.collection(COLLECTION_NAME).document(customer.getName())
				.set(customer);
		
		return collectionApiFuter.get().getUpdateTime().toString();
	}

	public List<Customer> getAllCustomers() throws InterruptedException, ExecutionException {

		Firestore db = FirestoreClient.getFirestore();
		Iterable<DocumentReference> docuIterable = db.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator = docuIterable.iterator();

		List<Customer> customers = new ArrayList<>();
		Customer customer;

		while (iterator.hasNext()) {

			DocumentReference documentReference = iterator.next();
			ApiFuture<DocumentSnapshot> future = documentReference.get();
			DocumentSnapshot document = future.get();

			customer = document.toObject(Customer.class);
			customers.add(customer);
		}

		return customers;
	}

	public String updateCustomer(Customer customer) throws InterruptedException, ExecutionException {

		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME)
				.document(customer.getName()).set(customer);
		return "Updated succefully at " + collectionApiFuture.get().getUpdateTime().toString();
	}

	public String deleteCutomer(String name) {

		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection(COLLECTION_NAME).document(name).delete();
		return "The item is deleted successfully";
	}

	public Customer getCustomerByName(String name) throws Exception, ExecutionException {

		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(name);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();

		if (document.exists()) {
			Customer customer = document.toObject(Customer.class);
			return customer;
		} else {
			return null;
		}

	}

}
