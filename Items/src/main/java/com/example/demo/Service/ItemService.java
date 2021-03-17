package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.demo.Model.Item;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ItemService {

	private static final String COLLECTION_NAME = "Items";

	public String saveItem(Item item) throws Exception, Exception {

		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuter = dbFirestore.collection(COLLECTION_NAME).document(item.getName())
				.set(item);

		return collectionApiFuter.get().getUpdateTime().toString();
	}
	
	
	public List<Item> getAllItems() throws InterruptedException, ExecutionException{
		
		Firestore db = FirestoreClient.getFirestore();
		Iterable<DocumentReference> docuIterable = db.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator = docuIterable.iterator();
		
		List<Item> items = new ArrayList<>();
		Item item;
		
		while (iterator.hasNext()) {
		
			DocumentReference documentReference = iterator.next();
			ApiFuture<DocumentSnapshot> future = documentReference.get();
			DocumentSnapshot document = future.get();
			
		   item = document.toObject(Item.class);
		   items.add(item);
		}
		
		return items;
	}
	
	
	public String updateItem(Item item) throws InterruptedException, ExecutionException {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult>  collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(item.getName()).set(item);
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	
	public String deleteItem(String name) {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection(COLLECTION_NAME).document(name).delete();
		return "The item is deleted successfully";
	}
	
	
	public Item getItemByName(String name) throws Exception, ExecutionException {
		
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(name);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		
		if(document.exists()) {
			Item item = document.toObject(Item.class);
			return item;
		}else {
			return null;
		}
		
	}

}
