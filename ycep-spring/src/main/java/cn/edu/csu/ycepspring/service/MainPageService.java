package cn.edu.csu.ycepspring.service;

import cn.edu.csu.ycepspring.entity.dto.KnowledgeAndGame;
import cn.edu.csu.ycepspring.entity.po.mongo.KnowledgeDocument;
import cn.edu.csu.ycepspring.entity.po.mysql.Game;
import cn.edu.csu.ycepspring.entity.po.mysql.Kind;

import java.util.List;
import java.util.Map;

public interface MainPageService {
    List<Kind> getKindAndItem();

    List<KnowledgeAndGame> getKnowledgeAndGame(int itemId);

    Map<String, Object> getKnowledgeAndGameByKind(int kindId, int pageNum, int pageSize);

    List<Game> getGameList(int knowledgeId);

    KnowledgeDocument getKnowledgeInfo(int id);


}
