package com.vinhsang.vivmall.presentation.ui.activity.main.cataloguefragment2.adapter;

import com.vinhsang.vivmall.domain.Catalogue;
import com.vinhsang.vivmall.presentation.model.BaseModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 8/9/2016.
 */

public class SectionDataModel extends BaseModel implements Serializable {

    private String headerTitle;
    private List<BaseModel> allItemsInSection = new ArrayList<>();
    private Catalogue catalogue ;

    public SectionDataModel(Catalogue catalogue) {
        this.catalogue = catalogue;
        this.headerTitle = catalogue.getTitle();
    }
    public SectionDataModel(Catalogue catalogue, ArrayList<BaseModel> allItemsInSection) {
        this.catalogue = catalogue;
        this.headerTitle = catalogue.getTitle();
        this.allItemsInSection = allItemsInSection;
    }



    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public List<BaseModel> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(List<BaseModel> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }


    @Override
    public int getModelType() {
        return ModelType.SECTION;
    }
}
