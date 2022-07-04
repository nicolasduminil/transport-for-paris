# transport-for-paris

mvn -DskipTests clean install

mvn verify

cd fuse/plan-journey-jax-rs

mvn wildfly:deploy

wget http://localhost:18080/plan-journey-jax-rs/
