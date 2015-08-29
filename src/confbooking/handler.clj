(ns confbooking.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [clojure.data.json :as json]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(require '[clojure.java.jdbc :as j])

(def mysql-db {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/confbooking"
               :user "root"
               :password "qwerty"})

(defn get-rooms [request]
    ; (println request)
    (pr-str (json/write-str (j/query mysql-db ["select * from rooms"]) )))


(defn get-room [request]
    (println request)
    (println ["select * from meetings where room_id ="((request :params) :room)])
    (pr-str (json/write-str (j/query mysql-db ["select * from rooms where room_id =?"((request :params) :room)]) ))	)


(defn book_room [request]
    (println request)
    (println ["insert into rooms where room_id ="((request :params) :room)])
    (pr-str (json/write-str (j/query mysql-db ["select * from rooms where room_id =?"((request :params) :room)]) ))	)


(defroutes app-routes
  (GET "/" [] (io/resource "public/index.html"))
  (GET "/api/get_rooms" [] get-rooms)
  (GET "/api/get_room" [] get-room)
  (GET "/book_room" [] (io/resource "public/book_room.html"))	
  (POST "/book_room" [] book_room)	
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
