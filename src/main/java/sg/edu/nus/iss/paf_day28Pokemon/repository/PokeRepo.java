package sg.edu.nus.iss.paf_day28Pokemon.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PokeRepo {

    @Autowired
    private MongoTemplate mongoTemplate;

    /*
     * db.mons.aggregate([
     *      { $unwind: 'type' },
     *      { $group: { _id: '$type'} },
     *      { $sort: { _id: 1 } }
     * ])
     */
    public List<String> getTypes(){

        UnwindOperation unwindType = Aggregation.unwind("type");

        GroupOperation groupType = Aggregation.group("type");

        SortOperation sortType = Aggregation.sort(Sort.by(Direction.ASC, "_id"));

        Aggregation pipeline = Aggregation.newAggregation(unwindType, groupType, sortType);

        return mongoTemplate.aggregate(pipeline, "mons", Document.class)
            .getMappedResults().stream()
            .map(d -> d.getString("_id"))
            .toList();
    }

    /*
     * db.mons.find({
     *      type:{
     *          $in: [ "Type" ]
     *      }
     * })
     */
    public List<Document> getPokemonByType(String type){
        Criteria criteria = Criteria.where("type")
            .in(type);

        Query query = Query.query(criteria);

        return mongoTemplate.find(query, Document.class, "mons");
    }
    
}
