package com.dev.smartmonitor.business.basic.basic;

import com.dev.smartmonitor.business.basic.delete.IDeleteFactory;
import com.dev.smartmonitor.business.basic.insert.IInsertFactory;
import com.dev.smartmonitor.business.basic.select.ISelectFactory;
import com.dev.smartmonitor.business.basic.update.IUpdateFactory;

public interface IBasicFactory {

    public IUpdateFactory createUpdateFactory();

    public IInsertFactory createInsertFactory();

    public IDeleteFactory createDeleteFactory();

    public ISelectFactory createSelectFactory();

}
