package eccrm.base.position.domain;


import com.ycrl.utils.tree.PathTree;
import com.ycrl.utils.tree.StaticTree;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Generated by chenl on 2014-10-13.
 */

public class Classify implements PathTree<Classify, String>, StaticTree<Classify, String> {

    private String id;
    private String name;
    private Classify parent;
    private String parentId;
    private String status;
    private String path;
    private Integer seqNo;
    private String creatorId;//计划人员
    private String creatorName;//计划人员
    private Date createdDatetime;//计划人员
    private String description;//计划人员
    @Transient
    private List<Classify> children;

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classify getParent() {
        return parent;
    }

    public void setParent(Classify parent) {
        this.parent = parent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Classify> getChildren() {
        return children;
    }

    public void setChildren(List<Classify> children) {
        this.children = children;
    }

    @Override
    public Classify addChild(Classify child) {
        if (children == null) {
            children = new ArrayList<Classify>();
        }
        children.add(child);
        return this;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
