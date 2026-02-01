package han.com.kg.bordoMal.dto.response;

import java.util.List;

public class ChatResponse {
    private Long id;
    private Long adId;
    private String adTitle;
    private Long buyerId;
    private String buyerName;
    private Long sellerId;
    private String sellerName;
    private List<MessageResponse> messages;

    public ChatResponse(Long id, Long adId, String adTitle, Long buyerId, String buyerName, Long sellerId, String sellerName, List<MessageResponse> messages) {
        this.id = id;
        this.adId = adId;
        this.adTitle = adTitle;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.messages = messages;
    }

    public ChatResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<MessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageResponse> messages) {
        this.messages = messages;
    }
}

