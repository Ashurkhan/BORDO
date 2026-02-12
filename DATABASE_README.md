com.example.livestock
│
├── config
│
├── domain
│   ├── user
│   │   ├── User.java
│   │   ├── Favourite.java
│   │   ├── UserRole.java
│   │   └── UserStatus.java
│   │
│   ├── ad
│   │   ├── Ad.java
│   │   ├── AdStatus.java
│   │   ├── Category.java
│   │   ├── Location.java
│   │   ├── Favorite.java
│   │   └── AdView.java
│   │
│   ├── media
│   │   ├── Image.java
│   │   └── ImageType.java
│   │
│   ├── chat
│   │   ├── Chat.java
│   │   ├── ChatUser.java
│   │   └── Message.java
│   │
│   ├── notification
│       ├── Notification.java
│       └── NotificationType.java
│   
│   
│       
│      
│
├── repository
│   ├── user
│   ├── ad
│   ├── chat
│   ├── notification
│
├── service
│   ├── user
│   ├── ad
│   ├── chat
│   └── notification
│
├── controller
│   ├── user
│   ├── ad
│   ├── chat
│   └── notification
│
└── LivestockApplication.java

/////
INSERT INTO categories ( name) VALUES
('Крупный рогатый скот'),
('Мелкий рогатый скот'),
( 'Лошади'),
( 'Птица'),
( 'Корма'),
( 'Прочее');