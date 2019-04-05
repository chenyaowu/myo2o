package com.chen.myo2o.service.impl;

import com.chen.myo2o.dao.ProductCategoryDao;
import com.chen.myo2o.dto.ProductCategoryExecution;
import com.chen.myo2o.entity.ProductCategory;
import com.chen.myo2o.enums.ProductCategoryStateEnum;
import com.chen.myo2o.exception.ProductCategoryOperationException;
import com.chen.myo2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }


    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
            try {
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("店铺创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (ProductCategoryOperationException e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error" + e.getMessage());
            }

        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }
    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
       //TODO 将此商品类别下的商品类别Id置空
        //删除该productCategory
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if(effectedNum<=0){
                throw  new ProductCategoryOperationException("商品删除失败");
            }else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (ProductCategoryOperationException e) {
            throw new ProductCategoryOperationException("deleteProductCategory error:"+e.getMessage());
        }
    }
}
