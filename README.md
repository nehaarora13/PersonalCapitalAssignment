
// Get Plan By Id
curl -X GET \
  http://ec2-35-153-127-20.compute-1.amazonaws.com:8080/investments/v1/plan/1 \
  -H 'Postman-Token: 666d5ea4-97e5-4e9c-aa98-98dcfd0032b8' \
  -H 'cache-control: no-cache'
  
  
  //Get Plan by name 
  curl -X GET \
  http://ec2-35-153-127-20.compute-1.amazonaws.com:8080/investments/v1/plan/name/PAIN%20CARE%20BOISE%20PROFIT%20SHARING%20PLAN \
  -H 'Postman-Token: 246a2be9-fe3c-4410-8d10-667267c8e9ae' \
  -H 'cache-control: no-cache'
  
  
  //Get Plan by sponsor name 
  curl -X GET \
  'http://ec2-35-153-127-20.compute-1.amazonaws.com:8080/investments/v1/plan/dfename/PAIN%20CARE%20CENTER%20BOISE,%20L.L.C.' \
  -H 'Postman-Token: 8edb1c1a-9f90-453b-8f46-10ad3278db7a' \
  -H 'cache-control: no-cache'
  
  
// Get Plan by Sponsor state

curl -X GET \
  http://ec2-35-153-127-20.compute-1.amazonaws.com:8080/investments/v1/plan/sponsorstate/CA \
  -H 'Postman-Token: 6e47c2aa-f96c-4f0b-9a75-f30cece6dbfb' \
  -H 'cache-control: no-cache'
