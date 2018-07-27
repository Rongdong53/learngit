package cn.wolfcode.wms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.query.QueryObject;

public interface ProductStockMapper {

    void insert(ProductStock record);

    void updateByPrimaryKey(ProductStock record);

	ProductStock selectByProductIdAndDepotId(@Param("productId")Long productId, @Param("depotId")Long depotId);
	
	int queryForCount(QueryObject qo);
    List<ProductStock> queryForList(QueryObject qo);
}