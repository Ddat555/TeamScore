package ru.teamscore.consumer;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.teamscore.common.entity.RawSensorData;

import java.time.LocalDateTime;
import java.util.List;

public class RawMessageRepository {

    public List<RawSensorData> findNewMessages(Session session, LocalDateTime lastProcessedTime) {
        if (lastProcessedTime == null) {
            String hql = "FROM RawSensorData r ORDER BY r.savedAt ASC";
            return session.createQuery(hql, RawSensorData.class)
                    .setMaxResults(20)
                    .getResultList();
        } else {
            String hql = "FROM RawSensorData r WHERE r.savedAt > :lastTime ORDER BY r.savedAt ASC";
            return session.createQuery(hql, RawSensorData.class)
                    .setParameter("lastTime", lastProcessedTime)
                    .setMaxResults(20)
                    .getResultList();
        }
    }

    public void deleteProcessedMessages(Session session, List<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }

        String hql = "DELETE FROM RawSensorData r WHERE r.id IN :ids";
        session.createQuery(hql)
                .setParameterList("ids", ids)
                .executeUpdate();
    }

    public int countUnprocessedMessages(Session session) {
        String hql = "SELECT COUNT(r) FROM RawSensorData r";
        Query<Long> query = session.createQuery(hql, Long.class);
        return query.uniqueResult().intValue();
    }
}