package com.dev.smartmonitor.persistence.dao.factory;

import android.content.Context;

public class DAOFactoryCreator {

    public IDAOFactory getFactry(Context context) {
        return new DAOFactory(context);
    }

}
