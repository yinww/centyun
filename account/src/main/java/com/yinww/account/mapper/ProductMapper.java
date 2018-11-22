package com.yinww.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yinww.account.domain.Product;
import com.yinww.account.table.KeyValuePair;

@Mapper
public interface ProductMapper {

	List<Product> getPageProducts(@Param("searchValue") String searchValue, @Param("orders") List<KeyValuePair> orders);

	Product getProductById(@Param("id") String id);
	
	void addProduct(Product product);
	
	void updateProduct(Product product);

	void deleteProduct(@Param("ids") List<String> ids);

}
