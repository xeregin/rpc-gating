def call(Map args){
  for (i=0; i<args.nodes.size(); i++){
    sh "echo 'Destroy Slave: ${args.nodes[i]}'"
  }
}
return this;
