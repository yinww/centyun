package com.yinww.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yinww.account.domain.Product;
import com.yinww.account.table.KeyValuePair;

@Mapper
public interface ProductMapper {

	List<Product> getPageProducts(@Param("searchValue") String searchValue, @Param("orders") List<KeyValuePair> orders);

	Product getProductById(@Param("id") Long id);
	
	void addProduct(Product product);
	
	void updateProduct(Product product);

	void updateStatus(@Param("ids") List<Long> ids, @Param("status") int status, @Param("editor") String editor);

	List<KeyValuePair> getAvailableProducts();

	int getProductByName(Product product);

}
