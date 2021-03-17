package com.example.demo.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Item;
import com.example.demo.Service.ItemService;

@RestController
@RequestMapping("api/")
public class ItemController {
	
	@Autowired
	private ItemService service;
	
	@PostMapping("/")
	public String saveItem(@RequestBody Item item) throws Exception {
		return service.saveItem(item);
	}
	
	
	@GetMapping("/")
	public List<Item> getAllItems() throws InterruptedException, ExecutionException{
		return service.getAllItems();
	}
	
	
	@PutMapping("/")
	public String updateItem(@RequestBody Item item) throws InterruptedException, ExecutionException {
		return service.updateItem(item);
	}
	
	
	@GetMapping("/{name}")
	public Item getItemByName(@PathVariable String name) throws ExecutionException, Exception {
		return service.getItemByName(name);
	}
	
	
	@DeleteMapping("/{name}")
	public String deleteItemByName(@PathVariable String name) {
		return service.deleteItem(name);
	}

}
