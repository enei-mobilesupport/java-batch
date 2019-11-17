package foo.sample.batch.job.step.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import foo.sample.batch.exception.ServiceException;
import foo.sample.db.auto.crud.ChargeMapper;
import foo.sample.db.auto.crud.ProvisioningLogMapper;
import foo.sample.db.auto.crud.ServiceProvisioningMapper;
import foo.sample.db.auto.crud.UserStatusMapper;
import foo.sample.db.auto.model.Charge;
import foo.sample.db.auto.model.ProvisioningLog;
import foo.sample.db.auto.model.ServiceProvisioning;
import foo.sample.db.auto.model.UserStatus;

public class DbItemWriter<T> implements ItemWriter<T> {

    @Autowired
    private ServiceProvisioningMapper serviceProvisioningMapper;
    @Autowired
    private ChargeMapper chargeMapper;
    @Autowired
    private UserStatusMapper userStatusMapper;
    @Autowired
    private ProvisioningLogMapper provisioningLogMapper;

    @Override
    public void write(List<? extends T> items) throws Exception {

        for (T item : items) {
            if (item instanceof ServiceProvisioning) {
                serviceProvisioningMapper.insertSelective((ServiceProvisioning) item);

            } else if (item instanceof Charge) {
                chargeMapper.insertSelective((Charge) item);

            } else if (item instanceof UserStatus) {
                userStatusMapper.insertSelective((UserStatus) item);

            } else if (item instanceof ProvisioningLog) {
                provisioningLogMapper.insertSelective((ProvisioningLog) item);

            } else {
                throw new ServiceException(".javaソースコードに問題があります。");
            }
        }
    }
}
