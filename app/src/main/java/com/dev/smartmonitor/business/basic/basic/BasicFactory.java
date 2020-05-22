package com.dev.smartmonitor.business.basic.basic;

import android.content.Context;

import com.dev.smartmonitor.business.basic.delete.DeleteFactoryCreator;
import com.dev.smartmonitor.business.basic.delete.IDeleteFactory;
import com.dev.smartmonitor.business.basic.insert.IInsertFactory;
import com.dev.smartmonitor.business.basic.insert.InsertFactoryCreator;
import com.dev.smartmonitor.business.basic.select.ISelectFactory;
import com.dev.smartmonitor.business.basic.select.SelectFactoryCreator;
import com.dev.smartmonitor.business.basic.update.IUpdateFactory;
import com.dev.smartmonitor.business.basic.update.UpdateFactoryCreator;

public class BasicFactory implements IBasicFactory {

    private static Context context;

    public BasicFactory(Context context) {
        this.context = context;
    }

    private static final UpdateFactoryCreator UPDATE_FACTORY_CREATOR = new UpdateFactoryCreator();
    public IUpdateFactory createUpdateFactory() {
        return UPDATE_FACTORY_CREATOR.getFactry(context);
    }

    private static final InsertFactoryCreator INSERT_FACTORY_CREATOR = new InsertFactoryCreator();
    public IInsertFactory createInsertFactory() {
        return INSERT_FACTORY_CREATOR.getFactry(context);
    }

    private static final DeleteFactoryCreator DELETE_FACTORY_CREATOR = new DeleteFactoryCreator();
    public IDeleteFactory createDeleteFactory() {
        return DELETE_FACTORY_CREATOR.getFactry(context);
    }

    private static final SelectFactoryCreator SELECT_FACTORY_CREATOR = new SelectFactoryCreator();
    public ISelectFactory createSelectFactory() {
        return SELECT_FACTORY_CREATOR.getFactry(context);
    }

}
