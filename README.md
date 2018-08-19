# MSDS692 - PII Clustering

## Project Description
During my last Data Science Data Science Practicum I created models to try to identify individuals that are the same person. I created two models to compare 2 inviduals to deteremine if they are the same person.  The first model was a random forest and the second model was a Logistic Regression.  The output of these models was the id of the two individuals compared and a flag indicating whether the model identified them as the same person.  

For this project I'm going to take the output of the models and create graphs of the individuals.  The goal is to create clusters or indiviudals that are the same person.  The reason for using a graph is that all the individuals may not have been linked together.  Somd individuals in the cluster might have been liked using another link in the graph.


## Data ##
 * The input to the models was around 20 billion compbonations of 2 individuals.  The output of the model was the same size. 
 * I took that output and removed any records where the models did not indciate the individual was the same person. The universe was around 7.5 billion people.  THis is the input I will use into the graph.
 * I also had to create a file that had all the distinct indiviudals in it.  That file was around 2 billion people.

## Data Analysis
 * For the graph I needed to make sure that each combonation of 2 individuals was only in the file once.  I did that by putting the minimum id in the first position and then did a distinct on the Universe.  The number of distinct values matched the input.  Based on this my universe was already distinct.
 
 * I wanted to get a sense of if there were any ids that mapped to a high number of other individuals.  To look at this I did a group by on the first individual id and got a count of the number of individuals each cluster had.  I then created a bar graph in Tableau show the distribution.
 
 * I also wanted to get a sense of the max number of clusters to expect.  Based on the graph I expected there to be around X clusters.  

## Data Cleansing and Preperation

## GraphX

## Resources
 * US Census Data - Population by State - https://www.census.gov/data/tables/2017/demo/popest/state-total.html#par_textimage_1574439295
 * US Census Quick Facts - https://www.census.gov/quickfacts/fact/table/US/PST045217
 * Example of linking records - https://towardsdatascience.com/record-linking-with-apache-sparks-mllib-graphx-d118c5f31f83
