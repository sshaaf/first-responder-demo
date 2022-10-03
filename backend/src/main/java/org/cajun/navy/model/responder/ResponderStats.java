package org.cajun.navy.model.responder;
import java.util.Objects;

public class ResponderStats {

    private final long active;
    private final long total;

    public ResponderStats(long active, long total) {
        this.active = active;
        this.total = total;
    }

    public long getActive() {
        return active;
    }

    public long getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponderStats that = (ResponderStats) o;
        return active == that.active &&
                total == that.total;
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, total);
    }

    @Override
    public String toString() {
        return "ResponderStats{" +
                "active=" + active +
                ", total=" + total +
                '}';
    }
}