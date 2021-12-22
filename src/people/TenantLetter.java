package people;

import rooms.Room;

import java.util.Objects;

public class TenantLetter {
    protected Person tenant;
    protected Room ap;

    public TenantLetter(Person p, Room a) {
        tenant = p;
        ap = a;
    }

    public Person getTenant() {
        return tenant;
    }
    public Room getAp() {
        return ap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantLetter that = (TenantLetter) o;
        return Objects.equals(tenant, that.tenant) &&
                Objects.equals(ap, that.ap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenant, ap);
    }
}
